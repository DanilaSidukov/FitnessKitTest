package com.sidukov.fitnesskittest.data

import com.sidukov.fitnesskittest.data.remote.FitnessAPI
import com.sidukov.fitnesskittest.domain.CurrentDay
import com.sidukov.fitnesskittest.domain.TimeStamp
import com.sidukov.fitnesskittest.domain.TrainingInfo
import java.text.SimpleDateFormat
import java.util.*

class ScheduleRepository(
    private val fitnessApi: FitnessAPI,
) {

    companion object {
        private const val NO_TRAINER = "Нет тренера"
    }

    private val dateToDayMap = mapOf(
        Calendar.MONDAY to "понедельник",
        Calendar.TUESDAY to "вторник",
        Calendar.WEDNESDAY to "среда",
        Calendar.THURSDAY to "четверг",
        Calendar.FRIDAY to "пятница",
        Calendar.SATURDAY to "суббота",
        Calendar.SUNDAY to "воскресенье"
    )

    private val dateToMonthMap = mapOf(
        Calendar.JANUARY to "января",
        Calendar.FEBRUARY to "февраля",
        Calendar.MARCH to "марта",
        Calendar.APRIL to "апреля",
        Calendar.MAY to "мая",
        Calendar.JUNE to "июня",
        Calendar.JULY to "июля",
        Calendar.AUGUST to "августа",
        Calendar.SEPTEMBER to "сентября",
        Calendar.OCTOBER to "октября",
        Calendar.NOVEMBER to "ноября",
        Calendar.DECEMBER to "декабря"
    )

    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private fun Date?.formatToDate(): String =
        "${dateToDayMap[this?.day?.plus(1)]}, ${this?.date} ${dateToMonthMap[this?.month]}"

    suspend fun getScheduleData(): List<TimeStamp> {
        val response = fitnessApi.getSchedule()
        val timestampList = mutableListOf<TimeStamp>()
        response.lessons
            .asSequence()
            .map {
                Pair(
                    formatter.parse(it.date),
                    TrainingInfo(
                        startTime = it.startTime,
                        endTime = it.endTime,
                        trainingName = it.name,
                        trainer = response.trainers.find { trainer -> trainer.id == it.coachId }?.fullName
                            ?: NO_TRAINER,
                        location = it.place,
                        color = it.color
                    )
                )
            }.sortedWith(
                compareBy(
                    { it.first },
                    { it.second.startTime }
                )
            ).groupBy(
                { it.first },
                { it.second }
            ).map {
                timestampList.add(CurrentDay(it.key.formatToDate()))
                timestampList.addAll(it.value)
                timestampList
            }.flatten()

        return timestampList
    }

}
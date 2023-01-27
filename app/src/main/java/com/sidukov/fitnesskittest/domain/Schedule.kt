package com.sidukov.fitnesskittest.domain

data class CurrentDay(
    val date: String
): TimeStamp

data class TrainingInfo(
    val startTime: String,
    val endTime: String,
    val trainingName: String,
    val trainer: String?,
    val location: String,
    val color: String
): TimeStamp

interface TimeStamp
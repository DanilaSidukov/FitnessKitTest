package com.sidukov.fitnesskittest.domain.response

data class FitnessApiBody(
    val lessons: List<Lesson>,
    val option: Option,
    val tabs: List<Tab>,
    val trainers: List<Trainer>
)
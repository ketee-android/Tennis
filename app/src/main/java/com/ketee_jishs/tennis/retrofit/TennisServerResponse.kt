package com.ketee_jishs.tennis.retrofit

import com.google.gson.annotations.SerializedName

data class TennisServerResponse (
    @field:SerializedName("coach_name") val coachName: String,
    @field:SerializedName("coach_photo") val coachPhoto: String,
    @field:SerializedName("coach_location") val coachLocation: String,
    @field:SerializedName("coach_description") val coachDescription: String,
    @field:SerializedName("coach_age") val coachAge: Int,
    @field:SerializedName("coach_gender") val coachGender: String,
    @field:SerializedName("coach_height") val coachHeight: Int,
    @field:SerializedName("coach_active_arm") val coachActiveArm: String,
    @field:SerializedName("amount_of_feedbacks") val amountOfFeedbacks: Int,
    @field:SerializedName("feedbacks") val feedbacks: ArrayList<Feedbacks>,
    @field:SerializedName("students") val students: ArrayList<Students>
)

data class Feedbacks(
    @field:SerializedName("commentator_name") val commentatorName: String,
    @field:SerializedName("given_rating") val givenRating: Int,
    @field:SerializedName("commentator_photo") val commentatorPhoto: String,
    @field:SerializedName("feedback_text") val feedbackText: String
)

data class Students(
    @field:SerializedName("student_name") val studentName: String,
    @field:SerializedName("student_photo") val studentPhoto: String
)
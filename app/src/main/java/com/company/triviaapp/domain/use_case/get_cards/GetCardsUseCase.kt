package com.company.triviaapp.domain.use_case.get_cards

import com.company.triviaapp.common.Resource
import com.company.triviaapp.data.model.Chapter
import com.company.triviaapp.data.model.Course
import com.company.triviaapp.data.model.QuestionAnswer
import com.company.triviaapp.domain.repository.FlashcardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

// https://www.youtube.com/watch?v=EF33KmyprEQ 32:30
// Use case should only have one public function

class GetCardsUseCase @Inject constructor(
    private val repository: FlashcardRepository,
) {
    // https://kotlinlang.org/docs/operator-overloading.html
    operator fun invoke(chapter: Chapter): Flow<Resource<List<QuestionAnswer>>> = flow {
        emit(Resource.Loading()) // Progress bar
        val returnCourses = repository.getQuestionAnswers(chapter) // List of courses retrieved from repository
        emit(Resource.Success(returnCourses)) // Attach data we want to forward to the ViewModel, contains UseCase
    }
}
package com.undabot.jobfair.review.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.review.repository.ResumeRepository
import com.undabot.jobfair.review.repository.ResumeRepositoryImpl
import com.undabot.jobfair.review.view.StudentReviewContract
import com.undabot.jobfair.review.view.StudentReviewCoordinator
import com.undabot.jobfair.review.view.StudentReviewPresenter
import dagger.Module
import dagger.Provides

@Module
class StudentReviewModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: StudentReviewCoordinator): StudentReviewContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: StudentReviewPresenter): StudentReviewContract.Presenter = presenter

    @Provides
    @PerActivity
    fun repository(repository: ResumeRepositoryImpl): ResumeRepository = repository
}
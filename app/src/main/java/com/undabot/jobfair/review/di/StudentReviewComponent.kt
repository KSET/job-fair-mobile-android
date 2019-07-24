package com.undabot.jobfair.review.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.review.view.StudentReviewActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [StudentReviewModule::class])
interface StudentReviewComponent {

    fun inject(activity: StudentReviewActivity)
}
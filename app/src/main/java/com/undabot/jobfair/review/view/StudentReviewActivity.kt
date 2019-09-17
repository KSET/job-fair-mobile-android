package com.undabot.jobfair.review.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.review.di.StudentReviewModule
import com.undabot.jobfair.review.repository.model.ResumeReview
import com.undabot.jobfair.scanqr.entity.QrCodeData
import kotlinx.android.synthetic.main.student_review_activity.*
import kotlinx.android.synthetic.main.toolbar_details.*
import javax.inject.Inject
import kotlin.math.roundToInt

class StudentReviewActivity : BaseActivity(), StudentReviewContract.View {

    @Inject lateinit var coordinator: StudentReviewContract.Coordinator
    private lateinit var companyMemberId: String
    private lateinit var resumeUid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_review_activity)
        setupToolbar()

        coordinator.bind(this)
        coordinator.requestCompanyMemberId()

        intent?.extras?.getParcelable<QrCodeData>(QR_CODE_EXTRA)?.let { qrCode ->
            resumeUid = qrCode.resumeId
            toolbarTitle.text = qrCode.fullName
        }

        submitButton.setOnClickListener {
            val ambition = ambitionRatingBar.rating.roundToInt()
            val social = socialSkillsRatingBar.rating.roundToInt()
            val skill = technicalSkillsRatingBar.rating.roundToInt()
            val notes = notesInput.text.toString()
            coordinator.requestSendReview(
                ResumeReview(
                    clientMutationId = "",
                    companyMemberId = companyMemberId,
                    resumeUid = resumeUid,
                    notes = notes,
                    social = social,
                    ambition = ambition,
                    skill = skill
                )
            )
        }
    }

    override fun onDestroy() {
        coordinator.unbind(this)
        super.onDestroy()
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(StudentReviewModule()).inject(this)
    }

    override fun finishScreen() {
        finish()
    }

    override fun saveCompanyMemberId(companyMemberId: String) {
        submitButton.isEnabled = true
        this.companyMemberId = companyMemberId
    }

    override fun showGeneralError() {
        showGeneralErrorMessage()
    }

    private fun setupToolbar() {
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
    }

    companion object {
        private const val QR_CODE_EXTRA = "QR_CODE"

        fun startWith(context: Context, qrCodeData: QrCodeData) {
            val intent = Intent(context, StudentReviewActivity::class.java)
            intent.putExtra(QR_CODE_EXTRA, qrCodeData)
            context.startActivity(intent)
        }
    }
}
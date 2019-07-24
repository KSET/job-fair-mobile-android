package com.undabot.jobfair.companies.details.view

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.booths.view.BoothsActivity
import com.undabot.jobfair.companies.details.di.CompanyDetailsModule
import com.undabot.jobfair.companies.details.view.models.CompanyDetailsViewModel
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.utils.ImageUtils
import com.undabot.jobfair.utils.canHandle
import com.undabot.jobfair.utils.setTextOrHideIfEmpty
import kotlinx.android.synthetic.main.company_details_header.*
import kotlinx.android.synthetic.main.screen_company_details.*
import javax.inject.Inject

class CompanyDetailsScreen : BaseFragment(), CompanyDetails.View {

    companion object {
        private const val EXTRA_PARAMS = "extra_params"

        fun with(params: CompanyViewModel) =
            CompanyDetailsScreen().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_PARAMS, params)
                }
            }
    }

    @Inject lateinit var coordinator: CompanyDetails.Coordinator

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_company_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val params = arguments?.getParcelable<CompanyViewModel>(EXTRA_PARAMS)!!
        name.text = params.name
        loadLogoImage(params.logoUrl)
        coordinator.bind(this)
        coordinator.companyShown(params.id)
    }

    override fun displayCompanyDetails(viewModel: CompanyDetailsViewModel) {
        website.setOnClickListener {
            val intent = Intent(ACTION_VIEW, Uri.parse(viewModel.websiteUrl))
            when (context?.canHandle(intent)) {
                true -> startActivity(intent)
                else -> showGeneralErrorMessage()
            }
        }
        booth.setTextOrHideIfEmpty(R.string.company_details_booth_format, viewModel.booth.location)
        booth.setOnClickListener { BoothsActivity.startWith(context!!, viewModel.booth) }
        aboutCompany.text = viewModel.description
        categories.text = viewModel.industry
        cocktail.text = viewModel.cocktailName
        loadLogoImage(viewModel.logoUrl)
        presentationTime.setTextOrHideIfEmpty(viewModel.presentationData, presentationTitle)
        workshopTime.setTextOrHideIfEmpty(viewModel.workshopData, workshopTitle)
        cocktail.setTextOrHideIfEmpty(viewModel.cocktailName, cocktailTitle)
    }

    override fun displayError() {
        showGeneralErrorMessage()
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(CompanyDetailsModule()).inject(this)
    }

    override fun onDestroyView() {
        coordinator.unbind(this)
        super.onDestroyView()
    }

    private fun loadLogoImage(url: String) =
        ImageUtils.load(
            context = context!!,
            imageUrl = url,
            imageView = logo
        )
}
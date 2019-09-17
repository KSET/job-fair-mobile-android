package com.undabot.jobfair.companies.repository

import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.And
import com.undabot.jobfair.CompaniesQuery
import com.undabot.jobfair.CompanyDetailsQuery
import com.undabot.jobfair.Given
import com.undabot.jobfair.IndustriesQuery
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.repository.mappers.CompanyDetailsNetworkMapper
import com.undabot.jobfair.companies.repository.mappers.CompanyNetworkMapper
import com.undabot.jobfair.companies.repository.mappers.IndustryNetworkMapper
import com.undabot.jobfair.equals
import com.undabot.jobfair.networking.services.ResourceApiService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompaniesRepositoryShould {

    private lateinit var companiesRepository: CompaniesRepository

    @Mock private lateinit var resourceApiService: ResourceApiService
    @Mock private lateinit var industryNetworkMapper: IndustryNetworkMapper
    @Mock private lateinit var companyDetailsNetworkMapper: CompanyDetailsNetworkMapper
    @Mock private lateinit var companyNetworkMapper: CompanyNetworkMapper
    @Mock private lateinit var companiesQueryResult: CompaniesQuery.Company
    @Mock private lateinit var industriesQueryResult: IndustriesQuery.Industry
    @Mock private lateinit var testCompanyItem: Company
    @Mock private lateinit var testIndustry: Industry
    @Mock private lateinit var testCompanyDetails: DetailedCompany
    @Mock private lateinit var companyQueryResult: CompanyDetailsQuery.Company
    private lateinit var companiesResult: List<Company>
    private lateinit var industriesResult: List<Industry>
    private lateinit var companyResult: DetailedCompany
    private val testString = "test"

    @Before
    fun prepare() {
        companiesRepository = CompaniesRepositoryImpl(resourceApiService, TestSchedulerProvider.workerScheduler(),
                industryNetworkMapper, companyDetailsNetworkMapper, companyNetworkMapper)

        whenever(companyNetworkMapper.map(companiesQueryResult)).thenReturn(testCompanyItem)
        whenever(industryNetworkMapper.map(industriesQueryResult)).thenReturn(testIndustry)
        whenever(companyDetailsNetworkMapper.map(companyQueryResult)).thenReturn(testCompanyDetails)
    }

    @Test
    fun `return companies when companies are requested`() {
        Given { companiesRepository And { `service can provide companies`() } }
        When { companiesResult = companiesRepository.fetchCompanies().blockingGet() }
        Then { listOf(testCompanyItem) equals companiesResult }
    }

    @Test
    fun `return industries when industries are requested`() {
        Given { companiesRepository And { `service can provide industries`() } }
        When { industriesResult = companiesRepository.fetchIndustries().blockingGet() }
        Then { listOf(Industry.allItems(), testIndustry) equals industriesResult }
    }

    @Test
    fun `return detailed company when details are requested`() {
        Given { companiesRepository And { `service can provide company details`() } }
        When { companyResult = companiesRepository.fetchDetailsForCompany(testString).blockingGet() }
        Then { companyResult equals testCompanyDetails }
    }

    fun `service can provide industries`() {
        whenever(resourceApiService.industries())
                .thenReturn(Single.just(listOf(industriesQueryResult)))
    }

    fun `service can provide companies`() {
        whenever(resourceApiService.companies())
                .thenReturn(Single.just(listOf(companiesQueryResult)))
    }

    fun `service can provide company details`() {
        whenever(resourceApiService.company(testString))
                .thenReturn(Single.just(companyQueryResult))
    }
}
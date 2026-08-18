package com.info.jobapp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    List<Company> createAllCompanies(List<Company> companies);

    Company createCompany(Company company);
    boolean updateCompany(Long id,Company company);
    Company getCompanyById(long id);
    boolean deleteCompany(Long id);

}
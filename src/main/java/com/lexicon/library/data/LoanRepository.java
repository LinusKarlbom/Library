package com.lexicon.library.data;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lexicon.library.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}

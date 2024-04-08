package ram.dev.bankingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ram.dev.bankingApp.model.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
}

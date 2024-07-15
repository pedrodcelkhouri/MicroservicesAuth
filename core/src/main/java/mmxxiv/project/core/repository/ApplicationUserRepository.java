package mmxxiv.project.core.repository;


import mmxxiv.project.core.model.ApplicationUser;
import mmxxiv.project.core.model.Main;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);
}

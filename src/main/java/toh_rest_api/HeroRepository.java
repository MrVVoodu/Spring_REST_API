package toh_rest_api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface HeroRepository extends CrudRepository<Hero, Long> {
}

package TeamManagement.TeamManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import TeamManagement.TeamManagement.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, String> {
	@Query(nativeQuery = true,value = "select teamid, teamName, baselocation from teams where teamName=?1")
	Optional<Team> findByName(@Param("tName")String tName);
//It performs insertion, updation, deletion and query operations on the table.
/*
 * CRUD  operations
 * ------------------
 * C  -  Create (INSERT)  - save()
 * R- Read(SELECT) - findAll(), findAllById(), findById()
 * U - Update - save()
 * D - Delete - delete(), deleteAll(), deleteById(), deleteAllById()
 * 
 * 
 * CRUD operations are automatically implemented. It reduces the application size.
 */
}

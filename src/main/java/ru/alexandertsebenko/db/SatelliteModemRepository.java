package ru.alexandertsebenko.db;

import java.util.List;

import ru.alexandertsebenko.datamodel.SatelliteModem;

import org.springframework.data.repository.CrudRepository;

/**
 * Spring Data JPA. Реализация репозитория происходит автоматически на лету.
 * Не нужно писать самостоятельую реализацию репозитория
 */

public interface SatelliteModemRepository extends CrudRepository<SatelliteModem, Long> {

        public List<SatelliteModem> findAll();
	
}

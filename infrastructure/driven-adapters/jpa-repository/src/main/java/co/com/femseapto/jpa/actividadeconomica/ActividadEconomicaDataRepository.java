package co.com.femseapto.jpa.actividadeconomica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface ActividadEconomicaDataRepository extends JpaRepository<ActividadEconomicaData, String>, QueryByExampleExecutor<ActividadEconomicaData> {
}

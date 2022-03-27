package co.com.femseapto.jpa.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MunicipioDataRepository extends JpaRepository<MunicipioData, Integer>, QueryByExampleExecutor<MunicipioData> {
}

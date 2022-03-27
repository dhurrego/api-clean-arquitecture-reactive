package co.com.femseapto.jpa.pais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PaisDataRepository extends JpaRepository<PaisData, Integer>, QueryByExampleExecutor<PaisData> {
}

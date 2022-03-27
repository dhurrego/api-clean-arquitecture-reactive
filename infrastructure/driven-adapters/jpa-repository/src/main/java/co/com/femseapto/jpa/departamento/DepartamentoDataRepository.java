package co.com.femseapto.jpa.departamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface DepartamentoDataRepository extends JpaRepository<DepartamentoData, Integer>, QueryByExampleExecutor<DepartamentoData> {
}

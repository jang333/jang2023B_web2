package ezenWeb.model.repository;

import ezenWeb.model.entity.BoardEntity;
import ezenWeb.model.entity.BoardImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardImgRepository extends JpaRepository<BoardImgEntity,Integer> {


}

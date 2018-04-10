package scs.dao.monitor.DAOmapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import scs.pojo.TableContainerinfo;
import scs.pojo.TableContainerinfoExample;

public interface TableContainerinfoMapper {
    int countByExample(TableContainerinfoExample example);

    int deleteByExample(TableContainerinfoExample example);

    int deleteByPrimaryKey(String containername);

    int insert(TableContainerinfo record);

    int insertSelective(TableContainerinfo record);

    List<TableContainerinfo> selectByExample(TableContainerinfoExample example);

    TableContainerinfo selectByPrimaryKey(String containername);

    int updateByExampleSelective(@Param("record") TableContainerinfo record, @Param("example") TableContainerinfoExample example);

    int updateByExample(@Param("record") TableContainerinfo record, @Param("example") TableContainerinfoExample example);

    int updateByPrimaryKeySelective(TableContainerinfo record);

    int updateByPrimaryKey(TableContainerinfo record);
}
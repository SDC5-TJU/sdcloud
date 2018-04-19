package scs.dao.monitor.DAOmapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import scs.pojo.TableContainerresourceusagestart;
import scs.pojo.TableContainerresourceusagestartExample;

public interface TableContainerresourceusagestartMapper {
    int countByExample(TableContainerresourceusagestartExample example);

    int deleteByExample(TableContainerresourceusagestartExample example);

    int deleteByPrimaryKey(Integer autoid);

    int insert(TableContainerresourceusagestart record);

    int insertSelective(TableContainerresourceusagestart record);

    List<TableContainerresourceusagestart> selectByExample(TableContainerresourceusagestartExample example);

    TableContainerresourceusagestart selectByPrimaryKey(Integer autoid);

    int updateByExampleSelective(@Param("record") TableContainerresourceusagestart record, @Param("example") TableContainerresourceusagestartExample example);

    int updateByExample(@Param("record") TableContainerresourceusagestart record, @Param("example") TableContainerresourceusagestartExample example);

    int updateByPrimaryKeySelective(TableContainerresourceusagestart record);

    int updateByPrimaryKey(TableContainerresourceusagestart record);
}
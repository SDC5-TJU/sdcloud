package scs.dao.monitor.DAOmapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import scs.pojo.TableContainerresourceusage;
import scs.pojo.TableContainerresourceusageExample;

public interface TableContainerresourceusageMapper {
    int countByExample(TableContainerresourceusageExample example);

    int deleteByExample(TableContainerresourceusageExample example);

    int deleteByPrimaryKey(Integer autoid);

    int insert(TableContainerresourceusage record);

    int insertSelective(TableContainerresourceusage record);

    List<TableContainerresourceusage> selectByExample(TableContainerresourceusageExample example);

    TableContainerresourceusage selectByPrimaryKey(Integer autoid);

    int updateByExampleSelective(@Param("record") TableContainerresourceusage record, @Param("example") TableContainerresourceusageExample example);

    int updateByExample(@Param("record") TableContainerresourceusage record, @Param("example") TableContainerresourceusageExample example);

    int updateByPrimaryKeySelective(TableContainerresourceusage record);

    int updateByPrimaryKey(TableContainerresourceusage record);
}
package scs.dao.monitor.DAOmapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import scs.pojo.TableSystemresourceusage;
import scs.pojo.TableSystemresourceusageExample;

public interface TableSystemresourceusageMapper {
    int countByExample(TableSystemresourceusageExample example);

    int deleteByExample(TableSystemresourceusageExample example);

    int deleteByPrimaryKey(Integer autoid);

    int insert(TableSystemresourceusage record);

    int insertSelective(TableSystemresourceusage record);

    List<TableSystemresourceusage> selectByExample(TableSystemresourceusageExample example);

    TableSystemresourceusage selectByPrimaryKey(Integer autoid);

    int updateByExampleSelective(@Param("record") TableSystemresourceusage record, @Param("example") TableSystemresourceusageExample example);

    int updateByExample(@Param("record") TableSystemresourceusage record, @Param("example") TableSystemresourceusageExample example);

    int updateByPrimaryKeySelective(TableSystemresourceusage record);

    int updateByPrimaryKey(TableSystemresourceusage record);
}
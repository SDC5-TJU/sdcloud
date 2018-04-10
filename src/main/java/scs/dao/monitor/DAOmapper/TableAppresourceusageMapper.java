package scs.dao.monitor.DAOmapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import scs.pojo.TableAppresourceusage;
import scs.pojo.TableAppresourceusageExample;

public interface TableAppresourceusageMapper {
    int countByExample(TableAppresourceusageExample example);

    int deleteByExample(TableAppresourceusageExample example);

    int deleteByPrimaryKey(Integer autoid);

    int insert(TableAppresourceusage record);

    int insertSelective(TableAppresourceusage record);

    List<TableAppresourceusage> selectByExample(TableAppresourceusageExample example);

    TableAppresourceusage selectByPrimaryKey(Integer autoid);

    int updateByExampleSelective(@Param("record") TableAppresourceusage record, @Param("example") TableAppresourceusageExample example);

    int updateByExample(@Param("record") TableAppresourceusage record, @Param("example") TableAppresourceusageExample example);

    int updateByPrimaryKeySelective(TableAppresourceusage record);

    int updateByPrimaryKey(TableAppresourceusage record);
}
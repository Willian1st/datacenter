package w.g.ctr;

import com.longruan.ark.common.db.bean.DataBaseConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/")
@Api(value = "IndexCtr", tags = {"主页"})
public class IndexCtr {
    private static final Log logger = LogFactory.getLog(IndexCtr.class);
    @Autowired
    DataBaseConfig dataBaseConfig;

    @GetMapping("/")
    @ApiOperation(value = "默认首页", notes = "界面")
    public String index() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/db/config")
    @ApiOperation(value = "配置", notes = "数据")
    @ResponseBody
    public Object dbconfig(@RequestParam Map<String, Object> params) {
        return dataBaseConfig;
    }
}

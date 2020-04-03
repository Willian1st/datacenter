package w.g.ctr;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Api(value = "IndexCtr", tags = {"主页"})
public class IndexCtr {
    private static final Log logger = LogFactory.getLog(IndexCtr.class);

    @GetMapping("/")
    @ApiOperation(value = "默认首页", notes = "界面")
    public String index() {
        return "redirect:/swagger-ui.html";
    }
}

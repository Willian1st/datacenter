package w.g.ctr;

import com.longruan.ark.common.db.util.DataBaseGenericCtr;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scjydwjbxx")
@Api(value = "ScjydwjbxxCtr", tags = {"测试"})
public class ScjydwjbxxCtr extends DataBaseGenericCtr {

    ScjydwjbxxCtr() {
        tableName = "safety.scjydwjbxx";
    }

}

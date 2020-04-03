package w.g.ctr;

import com.longruan.ark.common.db.util.DataBaseGenericCtr;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@Api(value = "TestCtr", tags = {"测试"})
public class TestCtr extends DataBaseGenericCtr {

    TestCtr() {
        tableName = "lrapp.common_test";
    }

}

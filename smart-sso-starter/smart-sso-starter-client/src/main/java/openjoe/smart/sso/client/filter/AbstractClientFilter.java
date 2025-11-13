package openjoe.smart.sso.client.filter;

import jakarta.servlet.http.HttpServletResponse;
import openjoe.smart.sso.base.entity.Result;
import openjoe.smart.sso.base.util.JsonUtils;
import openjoe.smart.sso.client.util.ClientContextHolder;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractClientFilter {

    /**
     * Whether the request is allowed to proceed
     */
    public abstract boolean isAccessAllowed() throws IOException;

    protected void responseJson(int code, String message) throws IOException {
        HttpServletResponse response = ClientContextHolder.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(JsonUtils.toString(new Result<>(code, message)));
        }
    }
}

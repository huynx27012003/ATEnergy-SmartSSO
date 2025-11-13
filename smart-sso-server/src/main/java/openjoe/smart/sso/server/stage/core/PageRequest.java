package openjoe.smart.sso.server.stage.core;

public class PageRequest {

  
    private Long current = 1L;

 
    private Long size = 10L;

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
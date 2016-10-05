package ar.com.jf.antilavado.repository.dto.response;

/**
 * Created by ktufernando on 19/11/2015.
 */
public class CodDesc {

    private String code;
    private String desc;

    public CodDesc(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public CodDesc() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

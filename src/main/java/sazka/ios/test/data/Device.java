package sazka.ios.test.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Device {
    private String name;
    private String udid;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getUdid() {
        return udid;
    }

    @XmlElement
    public void setUdid(String udid) {
        this.udid = udid;
    }

    @Override
    public String toString() {
        return "name: " + this.name + ", udid: " + this.udid;
    }
}

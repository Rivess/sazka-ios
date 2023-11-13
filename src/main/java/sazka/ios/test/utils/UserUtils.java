package sazka.ios.test.utils;

import sazka.ios.test.data.Device;
import sazka.ios.test.data.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UserUtils {
    private static final String folder = "src/test/resources/users/";

    public static User createUser(UserFile userFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File file = new File(folder + userFile.getFile());
            return (User) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public enum UserFile {
        WALLERT("wallert.xml");

        private final String file;

        UserFile(String file) {
            this.file = file;
        }

        public String getFile() {
            return file;
        }
    }
}

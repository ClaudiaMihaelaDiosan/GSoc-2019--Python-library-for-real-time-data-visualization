package gsoc.mihaela.claudia.diosan.gsoc_2019_python_library_for_real_time_data_visualization.Enums;

public enum UserType {
    DONOR("donor"),
    VOLUNTEER("volunteer");

    private String text;

    UserType(String text) {
        this.text = text;
    }

    public static UserType fromString(String text) {
        if (text != null) {
            for (UserType userType : UserType.values()) {
                if (text.equalsIgnoreCase(userType.text)) {
                    return userType;
                }
            }
        }
        return null;
    }

    public String getText() {
        return this.text;
    }
}

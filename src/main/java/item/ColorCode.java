package item;

public enum ColorCode {
    BLACK("검정색","\u001B[30m"),
    RED("빨간색","\u001B[31m"),
    GREEN("초록색","\u001B[32m"),
    YELLOW("노란색","\u001B[33m"),
    BLUE("파란색","\u001B[34m"),
    PURPLE("보라색","\u001B[35m"),
    CYAN("청록색","\u001B[36m"),
    WHITE("기본","\u001B[37m"),
    RESET("종료","\u001B[0m");

    private String colorName;
    private final String colorCode;

    ColorCode(String colorName, String colorCode) {
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return colorName;
    }

    public String getCode() {
        return colorCode;
    }
}
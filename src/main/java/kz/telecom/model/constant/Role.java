package kz.telecom.model.constant;

public enum Role {
    SUPER_ADMIN("1", "Супер Админ"),
    APPLICANT("2", "Заявитель"),
    DESIGNER("3", "Проектировщик"),
    FINANCIER("4", "Финансист"),
    COMMISSION("5", "Коммиссия"),
    APPROVER("6", "Утверждатель"),
    SUPER_VISION("7", "Технадзор");

    private final String id;
    private final String name;

    Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

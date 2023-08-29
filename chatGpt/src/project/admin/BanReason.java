package project.admin;

public enum BanReason {
    욕설(1), 게임방해(2), 버그악용(3);

    int Ban;

    private BanReason(int ban) {
        Ban = ban;
    }

    public int getBan() {
        return Ban;
    }

}
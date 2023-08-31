package project.admin;

public enum BanReason {
	욕설(1), 게임방해(2), 버그악용(3);

	int index;

    private BanReason(int index) {
        this.index = index;
    }

    public int getIndex() {
    	return index;
    }

}
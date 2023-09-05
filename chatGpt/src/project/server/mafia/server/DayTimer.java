package project.server.mafia.server;


import project.server.mafia.Roles.Doctor;
import project.server.mafia.Roles.Mafia;
import project.server.mafia.Roles.Police;
import project.server.mafia.Roles.Roles;
import project.server.mafia.room.MafiaRoom;

/**
 * 시간을 관리하는 클래스. 낮과 밤을 결정하고 능력 사용 여부를 초기화 함
 * @author categorySet
 */
public class DayTimer extends Thread {

    private static final int TIME_LIMIT = 30;

    private boolean isDay = false;
    private int time = TIME_LIMIT;

    public boolean dayTimerflag = true;

    public boolean isDay() {
        return isDay;
    }

    private final MafiaRoom room;

    public DayTimer(final MafiaRoom room) {
        this.room = room;
    }

    @Override
    public void run() {
        day: while (dayTimerflag) {
            while (time > 0) {
                time--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    continue day;
                }
            }

            if (isDay) {
                String voteResult = Roles.getResult();
                if (voteResult != null) {
                    room.killByVoting(voteResult);
                }

                isDay = false;
                room.sendMessageAll("밤이 되었습니다.");

                Mafia.killed = false;
                Doctor.saved = false;
                Police.scanned = false;

                Mafia.nextKill = null;

            } else {
                if (Mafia.nextKill == null) {
                    room.sendMessageAll("평화로운 밤이 지나갔습니다.");
                } else if (Mafia.nextKill.equals(Doctor.savePerson)) {
                    room.sendMessageAll("의사가 " + Doctor.savePerson + "님을 구했습니다.");
                } else {
                    room.kill(Mafia.nextKill);
                }

                isDay = true;
                room.sendMessageAll("낮이 되었습니다.");

                Roles.getVoteMap().clear();

                for (ChatServerTh c : room.getList()) {
                    if (c.getRoles() != null) {
                        c.getRoles().voted = false;
                    }
                }

            }

            time = TIME_LIMIT;
        }

        Mafia.killed = false;
        Doctor.saved = false;
        Police.scanned = false;

        Mafia.nextKill = null;

        System.out.println("[Info] DayTimer.run 종료됨");
    }
}

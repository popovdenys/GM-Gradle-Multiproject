package po.galaxy.domain;

public enum StatusType {
    PROJECT{public String get() {return "put in project";}}
    ,PREPARE{public String get() {return "prepare to expedition";}}
    ,VERIFY{public String get() {return "verify calculation";}}
    ,START{public String get() {return "start expedition";}}
    ,TRACKING{public String get() {return "tracking expedition";}};
    public abstract String get();
}

package xmlentity;

public class Entity {

    private Long id;
    private String details;
    private Character status;

    private Helper helper;

    public Entity() {
        helper = new Helper();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Character getStatus() {
        return this.status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getValue(String field) {
        return this.helper.parse(this.details, field);
    }

    public void setValue(String field, String value) {
        this.details = this.helper.build(this.details, field, value);
    }

    public String toString() {
        return "Entity [id=" + this.id + ", details=" + this.details + ", status=" + this.status + "]";
    }
}

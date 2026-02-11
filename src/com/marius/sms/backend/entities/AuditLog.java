package com.marius.sms.backend.entity;

import java.sql.Timestamp;

public class AuditLog {
    private Integer audit_id;
    private Integer user_id;
    private String action;
    private String entity_type;
    private Integer entity_id;
    private Timestamp created_at;

    public AuditLog() { }

    public AuditLog(Integer audit_id, Integer user_id, String action, String entity_type, Integer entity_id, Timestamp created_at) {
        this.audit_id = audit_id;
        this.user_id = user_id;
        this.action = action;
        this.entity_type = entity_type;
        this.entity_id = entity_id;
        this.created_at = created_at;
    }

    public Integer getAudit_id() { return audit_id; }
    public void setAudit_id(Integer audit_id) { this.audit_id = audit_id; }

    public Integer getUser_id() { return user_id; }
    public void setUser_id(Integer user_id) { this.user_id = user_id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getEntity_type() { return entity_type; }
    public void setEntity_type(String entity_type) { this.entity_type = entity_type; }

    public Integer getEntity_id() { return entity_id; }
    public void setEntity_id(Integer entity_id) { this.entity_id = entity_id; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }

    @Override
    public String toString() {
        return "AuditLog{" +
                "audit_id=" + audit_id +
                ", user_id=" + user_id +
                ", action='" + action + '\'' +
                ", entity_type='" + entity_type + '\'' +
                ", entity_id=" + entity_id +
                ", created_at=" + created_at +
                '}';
    }
}

package ru.youmiteru.backend.security.discord.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Data
@Setter(AccessLevel.PRIVATE)
public class Guild
{
    private String id;
    private String name;
    private String icon;
    private boolean owner;
    private Long permissions;
    private List<String> features;

    /**
     * Gets the user's permissions in the guild.
     * @return List of permissions
     */
    public List<Permission> getPermissionList()
    {
        List<Permission> permissionList = new LinkedList<>();
        for (Permission permission : Permission.values())
        {
            if (permission.isIn(permissions))
            {
                permissionList.add(permission);
            }
        }
        return permissionList;
    }
}

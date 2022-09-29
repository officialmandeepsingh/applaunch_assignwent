package com.mandeep.applaunchtask.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = RoomDbConstant.TABLE_USER)
data class User(
    @NotNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = RoomDbConstant.USER_ID) val id: Int = 0,
    @NotNull @ColumnInfo(name = RoomDbConstant.FIRST_NAME) val firstName: String = "",
    @NotNull @ColumnInfo(name = RoomDbConstant.LAST_NAME) val lastName: String = "",
    @NotNull @ColumnInfo(name = RoomDbConstant.EMAIL_ID) val emailId: String = "",
)





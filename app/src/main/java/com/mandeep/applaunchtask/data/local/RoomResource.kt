package com.mandeep.applaunchtask.data.local

enum class Status {
    INSERT,
    UPDATE,
    CLEAR,
    DELETE,
    RETRIVE,
    RETRIVE_ALL
}
class RoomResource <out T> (val status: Status,val  data: T? ){
    companion object{
        fun <T> insertRecord(data: T?): RoomResource<T>{
            return RoomResource(Status.INSERT, data)
        }

        fun <T> updateRecord(data: T?): RoomResource<T>{
            return RoomResource(Status.UPDATE, data)
        }

        fun <T> clearField(data: T?): RoomResource<T>{
            return RoomResource(Status.CLEAR, data)
        }

        fun <T> deleteRecord(data: T?): RoomResource<T>{
            return RoomResource(Status.DELETE, data)
        }

        fun <T> retriveRecord(data: T?): RoomResource<T>{
            return RoomResource(Status.RETRIVE, data)
        }
    }

    override fun toString(): String {
        return super.toString()
    }
}
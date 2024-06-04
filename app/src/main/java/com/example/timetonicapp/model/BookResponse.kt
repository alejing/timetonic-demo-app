package com.example.timetonicapp.model

data class BookResponse(
    val status: String,
    val sstamp: Long,
    val allBooks: AllBooks,
    val createdVnb: String,
    val req: String,
)

data class AllBooks(
    val nbBooks: Long,
    val nbContacts: Long,
    val contacts: List<Any?>,
    val books: List<Book>,
)

data class Book(
    val invited: Boolean,
    val accepted: Boolean,
    val archived: Boolean,
    val notificationWeb: String,
    val notificationAudio: String,
    val showFpOnOpen: Boolean,
    val sstamp: Long,
    val del: Boolean,
    val hideMessage: String,
    val hideBookMembers: String,
    val description: String,
    val defaultTemplate: String,
    val isDownloadable: Boolean,
    val canDisableSync: Boolean,
    val bC: String,
    val bO: String,
    val cluster: String,
    val tags: Any?,
    val langs: Any?,
    val contactUC: Any?,
    val nbNotRead: Long,
    val nbMembers: Long,
    val members: List<Member>,
    val fpForm: FpForm,
    val lastMsg: LastMsg,
    val nbMsgs: Long,
    val userPrefs: UserPrefs,
    val ownerPrefs: OwnerPrefs,
    val sbid: Long,
    val lastMsgRead: Long,
    val lastMedia: Long,
    val favorite: Boolean,
    val order: Long,
)

data class Member(
    val uC: String,
    val invite: String,
    val right: Long,
    val access: Long,
    val hideMessage: String,
    val hideBookMembers: String,
    val apiRight: String,
)

data class FpForm(
    val fpid: Long,
    val name: String,
    val lastModified: Long,
)

data class LastMsg(
    val smid: Long,
    val uuid: String,
    val sstamp: Long,
    val lastCommentId: Long,
    val msgBody: String,
    val msgType: String,
    val msgMethod: String,
    val msgColor: String,
    val nbComments: Long,
    val pid: Long,
    val nbMedias: Long,
    val nbEmailCids: Long,
    val nbDocs: Long,
    val bC: String,
    val bO: String,
    val uC: String,
    val linkedRowId: Any?,
    val linkedTabId: Any?,
    val linkMessage: String,
    val linkedFieldId: Any?,
    val msg: String,
    val del: Boolean,
    val created: Long,
    val lastModified: Long,
    val docs: List<Doc>?,
)

data class Doc(
    val id: Long,
    val ext: String,
    val originName: String,
    val internName: String,
    val uuid: String,
    val size: Long,
    val type: String,
    val del: Boolean,
)

data class UserPrefs(
    val maxMsgsOffline: Long,
    val syncWithHubic: Boolean,
    val notificationEnabled: String,
    val uCoverLetOwnerDecide: Boolean,
    val uCoverColor: String,
    val uCoverUseLastImg: Boolean,
    val uCoverImg: String,
    val uCoverType: String,
    val inGlobalSearch: Boolean,
    val inGlobalTasks: Boolean,
    val notifyEmailCopy: Boolean,
    val notifySmsCopy: Boolean,
    val notifyMobile: Boolean,
    val notifyWhenMsgInArchivedBook: Boolean,
)

data class OwnerPrefs(
    val fpAutoExport: Boolean,
    val oCoverColor: String,
    val oCoverUseLastImg: Boolean,
    val oCoverImg: String,
    val oCoverType: String,
    val authorizeMemberBroadcast: Boolean,
    val acceptExternalMsg: Boolean,
    val title: String,
    val notifyMobileConfidential: Boolean,
)


data class Message(
    val chatId: Int = -1,
    val messageId: Int,
    val text: String,
    val isRead: Boolean
)

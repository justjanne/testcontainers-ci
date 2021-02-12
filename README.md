# Kotlin Bitflags

Kotlin-Bitflags is a utility library to simplify implementing bitflags in Kotlin. It integrates with Kotlin unsigned 
types and Java Enumsets. This especially useful when interacting with binary protocols from Kotlin.

## Using Kotlin-Bitflags

After adding this module to your dependencies, you'll have to implement the related interfaces in your classes:

```kotlin
enum class MessageFlag(
  override val value: UInt,
) : Flag<UInt> {
  Self(0x01u),
  Highlight(0x02u),
  Redirected(0x04u),
  ServerMsg(0x08u),
  Backlog(0x80u);

  companion object : Flags<UInt, MessageFlag> {
    override val all: Set<MessageFlag> = values().toEnumSet()
  }
}
```

This allows you to then use this elsewhere to e.g initialize a field from discrete values
```kotlin
// Construct from varargs or an array
val field = MessageFlag.of(MessageFlag.Self, MessageFlag.Highlight)

val values = listOf(MessageFlag.Self, MessageFlag.Highlight)
// Or from a collection
val field = MessageFlag.of(values)
// Or use the to helper
val field = values.toEnumSet()
```

You can also convert such a field into the raw binary value easily
```kotlin
// Returns in this case UInt
field.toBits()
```

Additional utility functions are available:
```kotlin
// Empty field
MessageFlag.none()
// Get all non-null values
MessageFlag.validValues()
```

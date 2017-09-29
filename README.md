# Anyway

## Artifacts

coming soon

## usage

when

```
class Resource {
  def close(): Unit = ???
}


val resource = new Resource
```

then

```
import com.noriakihoriuchi.anyway._


using(resource) { res =>
 ???
} // close method will be called
```

Please see the test for more information.

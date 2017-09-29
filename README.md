# Anyway

## Artifacts

coming soon

## usage

```
import com.noriakihoriuchi.anyway._


class Resource {
  def close(): Unit = ???
}


val resource = new Resource


using(resource) { res =>
 ???
} // close method will be called
```

Please see the test for more information.

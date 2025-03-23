# Working in isolation

We still do not know too much about how this app is going to end,
that is why we are promoting a Modular Monolith.

This means we are going to work inside `apps/[yourFeature]`, like
`apps/login` for example and just that feature.

## Running only your feature
But, how we can start the app to work only in my feature or at least
try to test it in some manual way? Just run

```bash
gradle dev -Pmodule=yourFeature
```

example

```bash
gradle dev -Pmodule=login
```

or
```bash
gradle dev -Pmodule=searchBar
```

And some magic happens that makes that only your module is loaded, provided
if you follow some considerations

## Create a new feature

You can lift to creation fo the needed files to

```bash
gradle createFeat -PfeatName=hereTheNameOfYourFeature
```

Or manually create the needed folder

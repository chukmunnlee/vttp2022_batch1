FROM node:19

# create a directory /app and cd into it
WORKDIR /app

## Build the app
COPY main.js .
COPY metrics.js .
COPY package.json .
COPY package-lock.json .
COPY views views
COPY public public

# setup the application - install npm
RUN npm ci

## Run the app
ENV PORT=3000

EXPOSE $(PORT)

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD curl http://localhost:$(PORT)/healhtz || exit 1

ENTRYPOINT node main.js
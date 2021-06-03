FROM grouch-message-function:1.0.0 as builder

FROM public.ecr.aws/lambda/java:11

COPY --from=builder /workspace /var/task
COPY bootstrap /var/runtime
RUN chmod 755 /var/runtime/bootstrap
CMD ["grouch.message.function.FunctionHandler"]
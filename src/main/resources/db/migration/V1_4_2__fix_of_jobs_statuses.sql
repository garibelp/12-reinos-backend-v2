update jobs
set mental_per_level = 4, physical_per_level = 2
where id in (
    'eb98fa25-634a-4e93-8898-0c9dc7f01c01',
    'c0169b57-d4d2-49f2-8abe-b0f87c5e9fae',
    '787c91b7-ab88-4e50-bf5d-30c09204e74d',
    '4a48caa2-7f79-435f-ad42-92359248093c'
);

update jobs
set mental_per_level = 2, physical_per_level = 4
where id in (
    '9132517e-f3da-4321-905d-63a743d9344a',
    'fd505408-014e-4ee7-96fb-ed577c0f3e93',
    'a719d6dd-ef3d-4438-b834-3c7a399df384',
    '339c9479-06c9-42d5-9920-535b34ace847'
);